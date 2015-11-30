<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <title>atomantic/JavaScript-Playing-Cards @ GitHub</title>

    <link rel="stylesheet" type="text/css" media="all" href="playingCards.ui.css"/>
    <style type="text/css">
        body {
          margin-top: 1.0em;
          background-color: #fff;
          font-family: "Helvetica,Arial,FreeSans";
          color: #000000;
    }
    #container {
      margin: 0 auto;
      width: 860px;
    }
        h1 { font-size: 40px; color: #64052a; margin-bottom: 3px; }
        h1 .small { font-size: 0.4em; }
        h1 a { text-decoration: none }
        h2 { font-size: 1.5em; color: #64052a; }
    h3 { text-align: center; color: #64052a; }
    a { color: #64052a; }
    .description { font-size: 1.2em; margin-bottom: 30px; margin-top: 30px; font-style: italic;}
    .download { float: right; }
        pre { background: #000; color: #fff; padding: 15px;}
    hr { border: 0; width: 80%; border-bottom: 1px solid #aaa}
    .footer { text-align:center; padding-top:30px; font-style: italic; }


    h2{
        clear:both;
    }
    #error{
        display:none;color:#f00;border:1px solid #f60;padding:5px;margin:5px;
    }
    </style>

</head>

<body>
  <a href="http://github.com/atomantic/JavaScript-Playing-Cards"><img style="position: absolute; top: 0; right: 0; border: 0;" src="http://s3.amazonaws.com/github/ribbons/forkme_right_darkblue_121621.png" alt="Fork me on GitHub" /></a>

  <div id="container">

    <div class="download">
      <a href="http://github.com/atomantic/JavaScript-Playing-Cards/zipball/master">
        <img border="0" width="90" src="http://github.com/images/modules/download/zip.png"></a>
      <a href="http://github.com/atomantic/JavaScript-Playing-Cards/tarball/master">
        <img border="0" width="90" src="http://github.com/images/modules/download/tar.png"></a>
    </div>

    <h1><a href="http://github.com/atomantic/JavaScript-Playing-Cards">JavaScript Playing Cards</a>
      <span class="small">by <a href="http://github.com/atomantic">Adam Eivy (antic | atomantic)</a></span></h1>

    <div class="description">
      A playing card JavaScript and CSS library for creating standard deck games. The library is wrapped to allow it to act as a plugin for jquery (or another framework)
    </div>

    <p>This is really just a proof-of-concept that a deck of cards can be managed in JavaScript/CSS</p>
    <p>You might be interested in my hand-painted, beetle-themed playing card deck, which I'll be releasing to Kickstarter ~Aug 1, 2015: http://bit.ly/beetledeck</p>
    <h2>License</h2>
<p>MIT/GPL</p>
<br/>      </p>


    <h2>Demo</h2>
    <div id="error"></div>
    <input type="button" id="shuffler" value="shuffle" />
    <input type="button" id="draw" value="draw a card" />
    <input type="button" id="addCard" value="add drawn card back" />
    <input type="button" id="shuffleDraw" value="shuffle, then draw" />
    <input type="button" id="orderByRank" value="order by rank" />
    <input type="button" id="orderBySuit" value="order by suit" />
    <h2>Card Deck</h2>
    <div id="cardDeck"></div>
    <br />
    <h2>Drawn Cards</h2>
    <div id="yourHand"></div>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="playingCards.js"></script>
    <script type="text/javascript" src="playingCards.ui.js"></script>
    <script type="text/javascript">
        /*
         * example throwing cards on the table
         */
        $(document).ready(function(){
            var cardDeck = $("#cardDeck").playingCards();
            cardDeck.spread(); // show it

            var hand = [];
            var showError = function(msg){
                $('#error').html(msg).show();
                setTimeout(function(){
                    $('#error').fadeOut('slow');
                },3000);
            };
            var showHand = function(){
                var el = $('#yourHand');
                el.html('');
                for(var i=0;i<hand.length;i++){
                    el.append(hand[i].getHTML());
                }
            };
            var doShuffle = function(){
                cardDeck.shuffle();
                cardDeck.spread(); // update card table
                updateDeckIDs();
            };
            var doDrawCard = function(){
                var c = cardDeck.draw();
                if(!c){
                    showError('no more cards');
                    return;
                }
                hand[hand.length] = c;
                cardDeck.spread();
                updateDeckIDs();
                showHand();
             
            };
            var doPickCard = function(cardNum){      
                var c = cardDeck.pickCard(cardNum);
                if(!c){
                    var Deck = $("#cardDeck").children(".playingCard").map(function(){return this;}).get();
                    if(Deck.length > 0)
                    {
                        c = cardDeck.draw();
                    }
                    else
                    {
                    showError('no more cards');
                    return;
                    }
                }
                hand[hand.length] = c;
                cardDeck.spread();
                updateDeckIDs();
                showHand();
                updateHandIDs();
            };
            var doPutBackCard = function(cardid){  
                 var c = hand[Number(cardid)];
                if(!c){
                    if (hand.length > 0)
                    {
                       doAddCard();
                       return;
                    }
                    else
                    {
                    showError('no more cards');
                    return;
                    }
                }
                cardDeck.addCard(c);
                cardDeck.spread();
                updateDeckIDs();
                hand.splice(cardid, 1);
                showHand();
                updateHandIDs();
            };
            var doOrderByRank = function(){
                cardDeck.orderByRank();
                cardDeck.spread(); // update card table
                updateDeckIDs();
            };
            var doOrderBySuit = function(){
                cardDeck.orderBySuit();
                cardDeck.spread(); // update card table
                updateDeckIDs();
            };
            
            var updateDeckIDs = function(){
                var Deck = $("#cardDeck").children(".playingCard").map(function(){return this;}).get();
                for(var i=0;i<Deck.length;i++){
                   $(Deck[i]).attr('id',i);
                }
            };
            var updateHandIDs = function(){
                var Hand = $("#yourHand").children(".playingCard").map(function(){return this;}).get();
                for(var i=0;i<Hand.length;i++){
                   $(Hand[i]).attr('id',i);
                }
            };
            
            var doAddCard = function(){
               if(!hand.length){
                    showError('your hand is empty');
                    return;
                }
                var c = hand.pop();
                showHand();
                cardDeck.addCard(c);
                cardDeck.spread();
                 updateDeckIDs();
                updateHandIDs();  
                
            };
      
            $('#cardDeck').on('click', function (event) {
            var target = $(event.target).parents(".playingCard").map(function(){return this;}).get();
           
            doPickCard($(target[0]).attr('id'));
            }); 
         
            
             $('#yourHand').on('click', function (event) {
            var target = $(event.target).parents(".playingCard").map(function(){return this;}).get();
            doPutBackCard($(target[0]).attr('id'));
            }); 
             
            $('#shuffler').click(doShuffle);
            $('#draw').click(doDrawCard);
            $('#shuffleDraw').click(function(){
                doShuffle();
                doDrawCard();
            });
            
            $('#addCard').click(doAddCard);
            $('#orderByRank').click(doOrderByRank);
            $('#orderBySuit').click(doOrderBySuit);

        });
        /*
        // if we weren't using jquery to handle the document ready state, we would do this:
        if (window.addEventListener) {
            window.addEventListener("load",initPlayingCards,false);
        } else if (window.attachEvent) {
            window.attachEvent("onload",initPlayingCards);
        } else {
            window.onload = function() {initPlayingCards();}
        }
        function initPlayingCards() {
            cardDeck = new playingCards();
        }
        */
    </script>

    <h2>Download</h2>
    <p>
      You can download this project in either
      <a href="http://github.com/atomantic/JavaScript-Playing-Cards/zipball/master">zip</a> or
      <a href="http://github.com/atomantic/JavaScript-Playing-Cards/tarball/master">tar</a> formats.
    </p>
    <p>You can also clone the project with <a href="http://git-scm.com">Git</a>
      by running:
      <pre>$ git clone git://github.com/atomantic/JavaScript-Playing-Cards</pre>
    </p>

    <div class="footer">
      get the source code on GitHub : <a href="http://github.com/atomantic/JavaScript-Playing-Cards">atomantic/JavaScript-Playing-Cards</a>
    </div>

  </div>
</body>
</html>
?