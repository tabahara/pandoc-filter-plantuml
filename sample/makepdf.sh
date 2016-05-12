#!/bin/bash
pandoc -F plantuml-filter.eps.js --latex-engine=lualatex -V documentclass=ltjarticle -o devnote1.pdf overview.md  
