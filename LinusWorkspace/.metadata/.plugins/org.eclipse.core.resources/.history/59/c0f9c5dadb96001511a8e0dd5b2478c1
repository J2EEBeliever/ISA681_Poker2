/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISA681Poker;

import ISA681.CardDeck.Card;
import ISA681.CardDeck.DeckOfCards;
import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.xml.KXml2DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.tutorialspoint.struts2.HelloWorldAction;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author b1wolt
 */
public class jsonGetHand extends ActionSupport
{
	private final List<Card>Hand;
	final static Logger log = Logger.getLogger(HelloWorldAction.class);
	public jsonGetHand()
	{

		pokerHands pokerHands = new pokerHands();
		DeckOfCards deck = new DeckOfCards();
		deck.shuffle();
		List<Card> Hand1 = new ArrayList<>();
		List<Card> Hand2 = new ArrayList<>();
		deck.draw();
		for (int i = 0; i < 5; i++)
		{
			Hand1.add(deck.draw());
			Hand2.add(deck.draw());
		}

		pokerHands.setDeckOfCards(deck);
		pokerHands.setPlayer1Hand1(Hand1);
		pokerHands.setPlayer2Hand2(Hand2);
		NameCoder code = new XmlFriendlyNameCoder("_-", "_");
		XStream xstream = new XStream(new KXml2DomDriver(code));

		BufferedWriter writer = null;
		try
		{
			File logFile = new File("/Users/b1wolt/cardXML.xml");

			// This will output the full path where the file will be written to...
			System.out.println(logFile.getCanonicalPath());

			writer = new BufferedWriter(new FileWriter(logFile));
			writer.write(xstream.toXML(pokerHands));
		}
		catch (Exception e)
		{
			log.error(e);
		}
		finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) 
			{
				log.error(e);
			}
		}

		Hand = pokerHands.GetPlayer1Hand();

	}

	@Actions( {
		@Action(value = "/jsontable", results = {
				@Result(name = "success", type = "json")
		})
	})
	@Override
	public String execute()
	{
		return SUCCESS;

	}

	public List<Card> getHand()
	{
		return Hand;
	}




}
