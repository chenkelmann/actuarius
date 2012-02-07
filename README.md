##About Actuarius##
Actuarius is a Markdown Processor written in Scala using parser combinators. For detailled information, [head over to the Actuarius Wiki](https://github.com/chenkelmann/actuarius/wiki). 

The project homepage can be found on github: https://github.com/chenkelmann/actuarius

[To try Actuarius out, you can use the web dingus on my home page.](http://henkelmann.eu/projects/actuarius/dingus). (The web preview is currently broken but will be fixed (hopefully) soon.)

##License##
Actuarius is licensed under the 3-clause BSD license. For details see the `LICENSE` file that comes with the source.

##Compatibility##
Actuarius tries to stay as close to the original Markdown syntax definition as possible. There were however some quirks in the original Markdown I did not like. I wrote Actuarius as a Markdown processor for my homebrew blog engine, so I took the liberty to diverge slightly from the way the original Markdown works. The details are explained [in the respective article in the Actuarius Wiki](https://github.com/chenkelmann/actuarius/wiki/Differences-Between-Actuarius-And-Standard-Markdown)


##Maven##
The group id is `eu.henkelmann`, the artifact id is `actuarius_[scala-version]`, e.g.`actuarius_2.9.1`. The current stable version is 0.2.3. The repository url is http://maven.henkelmann.eu

There are builds for Scala 2.8.0, 2.8.1, 2.9.0-1 and 2.9.1. (How I hate Scala's binary incompatibilities…)

##sbt##
For sbt, define the repo like so:

    val repo = "Christophs Maven Repo" at "http://maven.henkelmann.eu/"
        
To add the lib to your project, add the following val:

    val actuarius = "eu.henkelmann" % "actuarius" % "0.2.3"
    
Currently, Actuarius itself is built using sbt 0.7.7.

##Version History##

###0.2.3###

* moved project to github
* added support for scala 2.9.1
* fixed bug that caused crashes when parsing windows line endings (CR LF)

