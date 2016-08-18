#!/usr/bin/env node

var pandoc = require('pandoc-filter');
var CodeBlock = pandoc.CodeBlock;
var Para = pandoc.Para;
var Image = pandoc.Image;
var Str = pandoc.Str;
var attributes = pandoc.attributes;

var java = require('java');

java.classpath.push(__dirname+'/lib/helper.jar');
java.classpath.push(__dirname+'/lib/plantuml.jar');
var plantUml = java.newInstanceSync("com.quinmantha.helper.PlantUML");

function generateSuffix(){
    return plantUml.randomSync();
}

var index = 1;
function action(type,value,format,meta) {
    if( type === 'CodeBlock' ) {
	if( value[0][1] == 'plantuml' ){
	    var suffix = generateSuffix();
	    var filename = "doc-files/file"+suffix+".png";
	    
	    index ++;
	    
	    var root =  meta['plantuml-root'];
	    var prefix = "";
	    if( root ){
		prefix = root['c'] + '/';
	    }
	    plantUml.processSync(value[1],prefix+filename);

	    return pandoc.Plain([Image(["",[],[]],[],[filename,""])]);
	    //return Para([Image(["",[],[]],[],[filename,""])]);	    
	    // Para([Image([ALT],["doc-files/file.svg","CAPTION"])]);
	}
    }
}

pandoc.stdio(action);
