xpath


https://www.freeformatter.com/xpath-tester.html

# XPath expression
1) //*[@tutId=04]
2) //*[@tutId=04]/descendant::section
3) //*[@name="core"]
4) //*[@name="core"]/parent::*
5) /tutorials/tutorial[4]/sections//section[3]
        Element='<section name="batch">Spring Batch</section>'
6) //*[@name="core"]/parent::*/child::section 
//*[@name="core"]/parent::/child::section
    Element='<section name="core">Spring Core</section>'
    Element='<section name="mvc">Spring MVC</section>'
    Element='<section name="batch">Spring Batch</section>'

```xml
<?xml version="1.0"?>
<tutorials>
	<tutorial tutId="01" type="java">
		<title>Guava</title>
		<description>Introduction to Guava</description>
		<date>04/04/2016</date>
		<author>GuavaAuthor</author>
	</tutorial>
	<tutorial tutId="02" type="java">
		<title>XML</title>
		<description>Introduction to XPath</description>
		<date>04/05/2016</date>
		<author>XMLAuthor</author>
	</tutorial>
	<tutorial tutId="03" type="android">
		<title>Android</title>
		<description>Introduction to Android</description>
		<date>04/03/2016</date>
		<author>AndroidAuthor</author>
	</tutorial>
	<tutorial tutId="04" type="java">
		<title>Spring</title>
		<description>Introduction to Spring</description>
		<date>04/02/2016</date>
		<author>SpringAuthor</author>
		<sections>
			<section name="core">Spring Core</section>
			<section name="mvc">Spring MVC</section>
			<section name="batch">Spring Batch</section>
		</sections>
	</tutorial>
</tutorials>
```

# http://xmltoolbox.appspot.com/xpath_generator.html
/tutorials/tutorial[4]/sections//section[3]@name
/tutorials//tutorial[4]@tutId
/tutorials/tutorial[4]/description/text()
/tutorials//tutorial[3]@type
