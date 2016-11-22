# HW5
Textual analysis of books 

CRCs

FileReader
- interface between program and input file 
  read the file line by line 
- future feature 
    - tell which part of the input file is the "book".
      - eliminate the empty line is already applied
    - tell where in the input file this line comes from.

Splitter
- receive one line at each time through readNextLine().
- analyze the string character by character
- Split the string into words and check whether the word being processed is in a quotation

CharactorSet
- take in new words from Splitter, update the number of charactors.
- use fixed array, use ascii equivalence as index


WordSet (Ec here)
- take in words from Splitter, store it in an Arraylist / hashmap maintain the number of occurrence
- the stop-list is a wordset

QuotationSet
- take in words from Splitter. record the length of quotations
- array list

UI
- prompt the user select a stoplist and create it.
- prompt the user select a book to analyze  
- create all the class mentioned above.


design

1) use fixed size array for storing char info, Arraylist for quotation.
2) use word length for quotation length;


flaws 
1) at least one file (tom-sawer) need to be re-save with encoding UTF-8 (On a Win 7 System). OtherWise the Scanner will not work as supposed.
2) at least one file (huck-finn) is in a poor format that this program could not handle the quotation correctly. The problem in this file is that there is sometimes one unexpected white space, "Harvey 'd" for example, and the single quotation mark here would be considered not as a part of a word "Harvey'd" but the starting or ending signal of a quotation.