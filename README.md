# QSLTagger

http://qsl.w8upd.org/

# What is it?

QSLTagger provides an interface for tagging, commenting on, and viewing scanned
QSL cards. It uses a database which holds information about the cards, such as
**How many cards there are**, **How many sides does each card have?**, and
**What is the filename that we should look at for each side?**.

How you get the information into the database is up to you (we'll likely be
open sourcing our importer script at some point, but it's highly specific to
the W8UPD club, which makes this not a huge priority right now).

QSLTagger uses the awesome SecureSocial Play plugin for authentication. Right
now, we only have **Facebook** authentication enabled and tested, but it should
be **relatively trivial** to add other authentication mechanisms.

The application is designed to be performant with a large number of QSL cards.
In testing, we've scanned and imported all of W8UPD's QSL cards, which
currently amount to **1211** cards.

In making the application performant at this scale, we've added things like
**endless scrolling** to pages (such as the Gallery) which show all cards,
rather than flooding the DOM with many cards at once, and (worse), depending
on the browser, locking the UI while it renders out the DOM tree. The point
here is that we've designed the application to be as fast as possible, even
when we have to show a lot of information. We also do our best to avoid slow
queries, and having issues like the 
**[N+1 problem](http://www.phabricator.com/docs/phabricator/article/Performance_N+1_Query_Problem.html)**.

We hope that our efforts pay off and that people will be able to easily
navigate through our collection of cards and be sent down memory lane as they
recall making some of the contacts that construct this collection of cards.

Currently, QSLTagger is somewhat specific to our usecase, but in the futue, it
would be nice to see it able to be deployed by other clubs. **If you have any
interest in deploying this application at your club, please contact us and we
can work with you to make setting it up go as smoothly as possible.**

# License

QSLTagger is released under an Apache 2 license - the same as the Play 
Framework on which it is based. See the LICENSE and NOTICE files for legal
information.
