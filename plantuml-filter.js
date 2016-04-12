#!/usr/bin/env node

var pandoc = require('my-pandoc-filter');
var CodeBlock = pandoc.CodeBlock;
var Para = pandoc.Para;
var Image = pandoc.Image;
var Str = pandoc.Str;
var attributes = pandoc.attributes;

var java = require('java');

java.classpath.push(__dirname+'/lib/helper.jar');
java.classpath.push(__dirname+'/lib/plantuml.jar');
var plantUml = java.newInstanceSync("com.quinmantha.helper.PlantUML");

var index = 1;
function action(type,value,format,meta) {
    if( type === 'CodeBlock' ) {
	if( value[0][1] == 'plantuml' ){
	    var filename = "doc-files/file"+index+".svg";
	    index ++;
	    plantUml.processSync(value[1],filename);
	    return Para([Image([],[filename,""])]);
	    // Para([Image([ALT],["doc-files/file.svg","CAPTION"])]);
	}
    }
}

pandoc.stdio(action);

