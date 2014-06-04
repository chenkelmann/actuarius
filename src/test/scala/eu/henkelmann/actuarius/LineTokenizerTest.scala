package eu.henkelmann.actuarius

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * Tests the Line Tokenizer that prepares input for parsing.
 */
@RunWith(classOf[JUnitRunner])
class LineTokenizerTest extends FlatSpec with ShouldMatchers{

	val lineTokenizer = new LineTokenizer
  
    "The LineTokenizer" should "split input lines correctly" in {
        lineTokenizer.splitLines("line1\nline2\n") should equal (List("line1", "line2"))
        lineTokenizer.splitLines("line1\nline2 no nl") should equal (List("line1", "line2 no nl"))
        lineTokenizer.splitLines("test1\n\ntest2\n") should equal (List("test1", "", "test2"))
        lineTokenizer.splitLines("test1\n\ntest2\n\n") should equal (List("test1", "", "test2"))
        lineTokenizer.splitLines("\n\n") should equal (Nil)
        lineTokenizer.splitLines("\n") should equal (Nil)
        lineTokenizer.splitLines("") should equal (List(""))
    }

    it should "preprocess the input correctly" in {
        lineTokenizer.tokenize("[foo]: http://example.com/  \"Optional Title Here\"") should equal(
            (new MarkdownLineReader(List(), Map( "foo"->new LinkDefinition("foo", "http://example.com/", Some("Optional Title Here")) )) ) )

        lineTokenizer.tokenize(
"""[Baz]:    http://foo.bar
'Title next line'
some text
> bla

[fOo]: http://www.example.com "A Title"
more text
[BAR]: <http://www.example.com/bla> (Also a title)"""
            ) should equal ( new MarkdownLineReader( List(
new OtherLine("some text"),
new BlockQuoteLine("> ", "bla"),
new EmptyLine(""),
new OtherLine("more text")
            ), Map (
"bar"->new LinkDefinition("bar", "http://www.example.com/bla", Some("Also a title")),
"baz"->new LinkDefinition("baz", "http://foo.bar", Some("Title next line")),
"foo"->new LinkDefinition("foo", "http://www.example.com", Some("A Title"))
    )))

    }

    it should "parse different line types" in {
        def p(line:String) = {
            lineTokenizer.lineToken(new LineReader(Seq(line))) match {
                case lineTokenizer.Success(result, _) => result
            }
        }
        p("a line")          should equal (new OtherLine("a line"))
        p("    a code line") should equal (new CodeLine("    ", "a code line"))
        p("#a header#")      should equal (new AtxHeaderLine("#", "a header#"))
        p("> a quote")       should equal (new BlockQuoteLine("> ", "a quote"))
        p(" \t ")            should equal (new EmptyLine(" \t "))
        p("* an item")       should equal (new UItemStartLine("* ", "an item"))
        p("- an item")       should equal (new UItemStartLine("- ", "an item"))
        p("+ an item")       should equal (new UItemStartLine("+ ", "an item"))
        p("===")             should equal (new SetExtHeaderLine("===", 1))
        p("---  ")           should equal (new SetExtHeaderLine("---  ", 2))
        p("- - -")           should equal (new RulerLine("- - -"))
    }
}