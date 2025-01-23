# Notebook

### Version 1 BCS
This is a project to explore a possible update to my BCS - Book Chapter Section system that I have used since about 2013 
for producing my online programming language tutorials. It was written in JavaScript, keeps an entire book in a single
HTML page, and allows the students to read the book section by section OR by skipping to the Table of Contents jump to any
particular section.

The sections themselves are composed primarily of paragraphs of English text with occasional inserts of Java code (or C code
or JavaScript). The code sections are false colored (similar to an IDE) to show keywords in one color, string constants in 
another, comments in yet another. 

It is basically a home grown markdown system that was just enough to get me the sorts of layouts that I wanted to achieve
in my teaching materials and it has served me well for over a decade.

I have been thinking for some time that I would like the system to be a little more dynamic in the following sense: I talk
about the code I am writing and show snippits and expect that the students can glue the little pieces of code into the
proper order to build the actual classes that make up the project, and every once in a while I clip out entire classes from
my project and show a "Here's what it should look like at this point."

What I would prefer is to make it possible at any point in the document to pull together and view any piece of the entire 
code base at that point along the development path. 

In my years of teaching classes using these documents as class notes, I have come to think of them as a superior way of
documenting code. What makes them superior is that actual projects locate code in the classes that they belong in and to
a noob trying to learn a code base, they have no idea what class they ought to look at first. Furthermore the comments in 
the code (if there are comments in the code) will typically try to point out relationships that were NOT obvious in the
first version of the code by had to change in later versions, or reference subtleties that make no sense to someone that 
has only a beginner's knowledge of how the system actually works. I have found that the historical development path through
the development of the project is a valuable alternative way to learn a code base rather than just reading the finished code.

So in the long run, I want to migrate my BCS system to be something that is not just a system for allowing a modestly dynamic
presentation (you can flip from page to page and you can hop to ANY section easily) of a single static text, but rather a tool
that can be used to produce more dynamic texts. I wants to migrate to being something MORE like an IDE.

My notion of the notebook is that when you start a project you start actually writing the commentary of what you are trying 
to build and what will be your first milestones and the code is just little snippets that fit into the document. In a
historical sense, I am doing something not terribly different from what Knuth did when he created the Tool that he used:
Web with Tangle and Weave, so that he could produce his own text books in TeX. He built a single source document which he
could either Tangle together into code that the compiler could read, or Weave it into a book document that would show his
progress/thinking as he worked through a system. 

What I expect to do in this project is: eventually I will bring my BCS system into this project as a starting place. I will 
then demo how it works by building a document that describes how the system works. If it goes no further than this, at least
other will have the same tools that I have used for the last decade to produce on-line books. I will then start up a document
using the BCS tools to describe the new code that I want to write as I write it.

And, yeah, this is nothing other than the first section, the Introduction, of that document that I intend to write.

Marlin Nov 24

Well, I have uploaded the BCS system but do not appear to be able to link to it directly here in this intro file. So if you want to see what a BCS html file it looks like you will need to Download the BCS folder onto your machine, look in the Books folder and locally launch one of the books, like BCSsystem.html, and let your browser display it. That book, if I remember correctly, tells you how to use the BCS system.

To actually read the docs on the notebook system that I am working on open up the BSCmjed.html file in the Books folder.

Marlin Dec 2024
