package application;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Spider
{
	//temp
	private static final int MAX_NUM_PAGES = 10;
	private Set<String> pagesVisited = new HashSet<String>();
	private List<String> pagesToVisit = new LinkedList<String>();
	
	
	public void searchNewsOutlet(String url, String searchWord)
	{
		String currentUrl = url;
		SpiderLeg leg = new SpiderLeg();
		
		//basically loop through news articles untill u hit the max number
		while(pagesVisited.size() < MAX_NUM_PAGES)
		{
			
			//while there are still pages to visit
			if((currentUrl = this.nextUrl(pagesToVisit.get(0))) != null)
			{
				//basically this spider class is going to facilitate the jumping between articles while the leg class
				//is actually going to be searching the articles
				leg.crawl(currentUrl);
				
				//a little accounting to keep track of pages to visit and pages visited
				pagesVisited.add(currentUrl);
				pagesToVisit.remove(currentUrl);
				
				//gonna have to place a limit on this --> only search one "accessory article" ..... cant keep going down the list of link
				//if every news artice is going to have a link to another  
				pagesToVisit.addAll(leg.getLinks());
				
				
				
				boolean success = leg.searchForWord(searchWord);
				
				if(success)
				{
					System.out.println("Yay");
					System.out.println("it was a success");
				}
			}
			else
			{
				break;
			}
		}
	}
	
	public String nextUrl(String url)
	{
		String curr = url;
		while(pagesVisited.contains(curr))
		{
			pagesToVisit.remove(url);
			curr = pagesToVisit.get(0);
		}
		return curr;
	}

	
}
