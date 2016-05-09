# Build

$ npm install
$ cd ~/bin; ln -s plantuml-filter.js


# How to use

pandoc -F plantuml-filter.js input-file.md


# set root folder for html
if you want to change a location for images, you can set a metadata for it.


pandoc --filter=plantuml-filter.js --metadata=plantuml-root:./html input-file.md

## FYI. How to install pandoc ?
the pandoc in Debian:jessie is old.
you have to install it from https://github.com/jgm/pandoc/releases/tag/1.17.0.2 (at this moment). The pandoc provided by Debian and official one are slightly different, you have to use new official one.


