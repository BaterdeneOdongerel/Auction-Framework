Auction Framework


Problem

Every auction system operates in a very similar manner:
+ Create and manage auctions
+ For each of the auction, allow users to bid
+ Announce the winner
+ Manage users

The items that people bid on vary from system to system but those operations are standardized
and should be found in one form or another in this type of applications.
Bloggers have Wordpress, Ghost. eCommerce has Wix, Shopify. But why no system out there
for folks that desperately need an auction system?

Solution

Create a framework from which developers can generate a backend of an auction system.
Then create a web application that use the framework to verify and demonstrate the usability of
the framework.

The framework will provide
+ A template to allow developer to define their own item object
+ Operations related to managing an auction system:
+ Create Auction
+ Managing auctions
+ Allow users to bid
+ Decide and announcing the winner.
+ Operations related to managing a multiple users system:
+ Create User
+ Update User
+ Delete User
+ Enable/Disable User
+ Email User.

The web application will have these features

+ Log in, log out, sign up, forget password
+ Auctions listing
+ Bid operating
+ Admin dashboard to: manage auctions, users and manage bids for each auction
+ Emailing users



Patterns:

# Pattern                       Class
1 Singleton                     DatabaseProp, EmailProp, MessagesProp, SMSProp
2 Builder                       Option
3 Factory                       TraceFactory
4 Template                      BaseCommunicationService, BaseServlet,AbstractAuctionTemplate
5 Iterator                      Aggregate, ConcreteIterator, Iterator
6 Facade                        ProductService, EventService, CategoryService,BidService, AuctionService
7 Observer                      TaskQueue
8 Strategy                      CommunicationServiceImpl
9 Mediator                      BaseServlet

10 Bridge                       CrudTemplate, Services
