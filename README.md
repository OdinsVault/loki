# Source-Code-Mapper

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/32a0f8c0f3984207b75fb03671354bc2)](https://app.codacy.com/manual/vmbckscc456/Source-Code-Mapper?utm_source=github.com&utm_medium=referral&utm_content=buddhikac96/Source-Code-Mapper&utm_campaign=Badge_Grade_Dashboard)

## Description
**'Simply'** is a beginner-friendly, simplified and extensible programming language which is designed to support novice learners in understanding the basic concepts of programming. It's reduced syntax also supports translatibility, allowing users to write code in supported native languages instead of English. Development efforts of Simply are currently in progress and are being carried out in different phases.
-  Simply language design and transpiler
-  Coding playground supporting learning and program visualization
-  Utility tools for translatability and syntax highlighting

Source-Code-Mapper is a utility tool which handles Simply's language extensibility feature by mapping native language source code into target language (English) source code before transpilation.

Source-code-mapper currently consist two main components.
1. Mapper
2. Localizer

### Mapper

Users can use mapper to translate native language code
(Ex - Source code written in Sinhala) to English.

### Localizer

Users can use localizer to translate source code written in English
to native language.

## How to use

#### mapper

* Command
  * **mapper**
* Options
  * --sourceFile | -src
    * Source code file location
  * --nativeLangId | -n
    * Source native language id

Execute mapper

<b>java -jar mapper -src "c://documents//codes//sample.simply" -n sn</b>

#### localizer

* Command
  * **localizer**
* Options
  * --sourceFile | -src
    * Source code file location
  * --targetLngId | -t
    * Target native language id

Users can find the native language id from [mappings registry](mappings/MappingRegistry.yml)

## Building from Source

## Contribute
