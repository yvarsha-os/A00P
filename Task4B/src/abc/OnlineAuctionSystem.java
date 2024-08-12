package abc;

import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Bidder {
    void update(String message);
}

// Concrete Observer
class ConcreteBidder implements Bidder {
    private String bidderName;

    public ConcreteBidder(String bidderName) {
        this.bidderName = bidderName;
    }

    @Override
    public void update(String message) {
        System.out.println("Notification to " + bidderName + ": " + message);
    }

    public String getName() {
        return bidderName;
    }
}

// Subject interface
interface AuctionEvent {
    void registerBidder(Bidder bidder);
    void removeBidder(Bidder bidder);
    void notifyBidders(String message);
}

// Concrete Subject
class Auction implements AuctionEvent {
    private List<Bidder> bidders = new ArrayList<>();
    private String itemName;

    public Auction(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public void registerBidder(Bidder bidder) {
        bidders.add(bidder);
    }

    @Override
    public void removeBidder(Bidder bidder) {
        bidders.remove(bidder);
    }

    @Override
    public void notifyBidders(String message) {
        for (Bidder bidder : bidders) {
            bidder.update(message);
        }
    }

    public void itemAvailable() {
        notifyBidders("The item '" + itemName + "' is now available for bidding.");
    }

    public void startBidding() {
        notifyBidders("Bidding has started for the item '" + itemName + "'.");
    }

    public void endBidding() {
        notifyBidders("Bidding has ended for the item '" + itemName + "'.");
    }
}

// Main application class
public class OnlineAuctionSystem {
    public static void main(String[] args) {
        // Create an auction for an item
        Auction auction = new Auction("Antique Vase");

        // Create bidders
        Bidder bidder1 = new ConcreteBidder("Bidder 1");
        Bidder bidder2 = new ConcreteBidder("Bidder 2");
        Bidder bidder3 = new ConcreteBidder("Bidder 3");

        // Register bidders to the auction
        auction.registerBidder(bidder1);
        auction.registerBidder(bidder2);
        auction.registerBidder(bidder3);

        // Simulate auction events
        auction.itemAvailable();
        auction.startBidding();

        // Unsubscribe a bidder
        auction.removeBidder(bidder2);

        auction.endBidding();
    }
}
