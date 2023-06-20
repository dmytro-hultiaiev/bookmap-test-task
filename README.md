# bookmap-test-task

Simulation of the order book and getting the necessary data from this order book using the commands below

Commands for the order book come from the input file, the result is written to the output file. Each line in the file can be one of the following:

Updates to the limit order book in the following format:

• u,(price),(size),bid - set bid size at (price) to (size)

• u,(price),(size),ask - set ask size at (price) to (size)
  
Queries in the following format:

• q,best_bid - print best bid price and size

• q,best_ask - print best ask price and size

• q,size,(price) - print size at specified price (bid, ask or spread).
  
And market orders in the following format:

• o,buy,(size) - removes (size) shares out of asks, most cheap ones.

• o,sell,(size) - removes (size) shares out of bids, most expensive ones
