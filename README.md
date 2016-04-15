# Build

$ npm install
$ cd ~/bin; ln -s plantuml-filter.js


# How to use

pandoc -F plantuml-filter.js input-file.md


# set root folder for html
if you want to change a location for images, you can set a metadata for it.


pandoc --filter=plantuml-filter.js --metadata=plantuml-root:./html input-file.md



