
public class PEWordScanner {
	
	protected ElementsList list = new ElementsList();
	protected int totalMatches;
	protected String[] matchedWords;//Holds the words that are matched fully.
	protected String[] matches;//Holds the matches for the words in the matchedWords variable.
	private boolean finished;//Holds whether the scan is finished or not.
	
	public PEWordScanner()
	{
		matchedWords = new String[WordList.numberOfWords()];
		matches = new String[WordList.numberOfWords()];
		
		totalMatches = 0;
	}

	Thread operate = new Thread("OPERATE") {
		
		public void run() {//Begins the operation of comparing words to symbols.
			
			for(int i = 0;i < WordList.numberOfWords();i++)//Loop that iterates through each word from the list of words.
			{
				String word = WordList.getWordAtIndex(i).toLowerCase();//Contains the word that is going to be tested, in lower case.
				
				String[] result = SegmentString(word);//Contains the result from the segmenting function.
				
				if(result[0] != null)//If the result is not null, then there was a match.
				{
					for(int j = 0;j < 20;j++)
					{
						if(result[j] != null)
						{
							System.out.println(capitalizeFirstLetter(word) + ": " + result);//Temporary, shows the user the word, and the result.
							continue;//Continues on to the next word.
						}
						else break;
					}
				}
				else if(result[0] == null)//If the result is null, then there was no match.
				{
					System.out.println("No match found for: " + capitalizeFirstLetter(word));//Temporary, shows the user that there was no match for the word.
					continue;//Continues on to the next word.
				}
			}//End of the loop
			
			setFinished(true);
		}
	};
	
	
	protected String[] SegmentString(String input) {//Segmenting function, takes a word, or a portion of a word, and tests for a possible combination of Symbols that can make the word/portion.
		//
		//This function uses recursive backtracking in order to find if there is a match.
		//
		//Tests to see if a match can be found from the beginning of the word, and if it can, then it continues to test the entire word.
		//If it does not find a match from the beginning, the word can not be entirely made of symbols at all.
		//
		
		String[] foundMatches = new String[20];
		
		  if (list.isASym(input)) foundMatches[0] = capitalizeFirstLetter(input);//If the word matches one of the Symbols without modification, return the word.
		  int len = input.length();//Sets this variable to the length of the word for use in the loop.
		  for (int i = 1; i < len; i++) {//Loops through to the length of the word.
		    String prefix = input.substring(0, i);//Sets the prefix to be tested to the beginning of the word, 0, to the current position in the iteration.
		    if (list.isASym(prefix)) {//If the beginning of the word matches a symbol, then continue on to test the whole word.
		      String suffix = input.substring(i, len);//Sets the suffix to be tested starting from the position after the prefix, to the end of the word (length).
		      String[] segSuffix = SegmentString(suffix);//Sets the test of the suffix to this variable.
		      if (segSuffix[0] != null) {//Checks to see if the above variable is not null, if it isn't, then we have a match.
		    	  for(int j = 0;j < 20;j++)
		    	  {
		    		  if(segSuffix[j] == null)
    				  {
	    				  break;
		    		  }
		    		  else
		    		  {
		    			  for(int k = 0;k < 20;k++)
		    			  {
		    			  	if(foundMatches[k] == null)
		    			  	{	
		    			  		foundMatches[j] = capitalizeFirstLetter(prefix) + " + " + capitalizeFirstLetter(segSuffix[j]);
		    			  	}
		    			  	else continue;
		    			  }
		    			  continue;
		    		  }
		    	  }
		      }//If the suffix is null, then we need to try another prefix. The loop will try again starting from the beginning of the word to the next position in the iteration.
		    }
		  }
		  return foundMatches;//If no full match is found, return null.
		}
	
	protected String capitalizeFirstLetter(String original){//For display purposes only, capitalizes the first letter in a given string and capitalizes it.
	    if(original.length() == 0)//If the length of the word received is 0, obviously we can't capitalize anything.
	        return original;//Return the original, probably empty, string.
	    return original.substring(0, 1).toUpperCase() + original.substring(1);//Split the first letter and return it capitalized, add the rest, and then return the whole thing.
	}
	
	public void resetMatches() {
		totalMatches = 0;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
}
