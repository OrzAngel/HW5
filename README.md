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
- receive one line at each time from FileReader.
- analyze the string character by character
- Split the string into words and check whether the word being processed is in a quotation

CharactorSet
- take in new words from Splitor, update the number of charactors.
- use fixed array, use ascii equivalence as index


WordSet (Ec here)
- take in words from Splitor, store it in an Arraylist / hashmap maintain the number of occurance
- the stop-list is a wordset

QuotationSet
- take in words from splitor. record the length of quotations
- array list

UI
- prompt the user select a stoplist and create it.
- prompt the user select a book to analyze  
- create all the class mentioned above.


design

1) use fixed size array for storing char info, Arraylist for quotation.
2) use word length for quotation length;
