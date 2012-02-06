package eu.henkelmann.actuarius

import java.io.{InputStreamReader, StringWriter}

/**
 * This is the Transformer that uses the other parsers to transform markdown into xhtml.
 * Mix this trait in if you want more control over the output (like switching verbatim xml on/off or using
 * different opening/closing tags for the output).
 */
trait Transformer {

    /**
     * Overwrite this method to return a custom decorator if you want modified output.
     */
    def deco():Decorator = Decorator

    private object lineTokenizer extends LineTokenizer {
        override def allowXmlBlocks() = Transformer.this.deco().allowVerbatimXml()
    }
    private object blockParser extends BlockParsers {
        override def deco() = Transformer.this.deco()
    }

    /**
     * This is the method that turns markdown source into xhtml.
     */
    def apply(s:String) = {
        //first, run the input through the line tokenizer
        val lineReader = lineTokenizer.tokenize(s)
        //then, run it through the block parser
        blockParser(lineReader)
    }
}

/**
 * Simple Standalone Markdown transformer.
 * Use this if you simply want to transform a block of markdown without any special options.
 * val input:String = ...
 * val xhtml:String = new ActuariusTransformer()(input)
 *
 * Note that Actuarius and hence this class is not thread-safe.
 * This is because it is based on Scala Parser Combinators which are not thread-safe :(
 * (though they should be IMHO)
 */
class ActuariusTransformer extends Transformer


/**
 * Contains a main methdod that simply reads everything from stdin, parses it as markdown and
 * prints the result to stdout.
 */
object ActuariusApp extends Transformer {


    def main(args:Array[String]) = {
        //read from system input stream
        val reader = new InputStreamReader(System.in)
        val writer = new StringWriter()
        val buffer = new Array[Char](1024)
		var read = reader.read(buffer)
		while (read != -1) {
			writer.write(buffer, 0, read)
			read = reader.read(buffer)
		}
        //turn read input into a string
        val input = writer.toString
        //run that string through the transformer trait's apply method
        val output = apply(input)
        //print result to stdout
        print(output)
    }
}