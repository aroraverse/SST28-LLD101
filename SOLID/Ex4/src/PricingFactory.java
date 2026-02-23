import java.util.HashMap;
import java.util.Map;

public class PricingFactory {
    private static final Map<Integer, RoomPricingStrategy> roomPricingMap = new HashMap<>();
    private static final Map<AddOn, AddOnPricing> addOnPricingMap = new HashMap<>();
    
    static {
        roomPricingMap.put(LegacyRoomTypes.SINGLE, new SingleRoomPricing());
        roomPricingMap.put(LegacyRoomTypes.DOUBLE, new DoubleRoomPricing());
        roomPricingMap.put(LegacyRoomTypes.TRIPLE, new TripleRoomPricing());
        roomPricingMap.put(LegacyRoomTypes.DELUXE, new DeluxeRoomPricing());
        
        addOnPricingMap.put(AddOn.MESS, new MessPricing());
        addOnPricingMap.put(AddOn.LAUNDRY, new LaundryPricing());
        addOnPricingMap.put(AddOn.GYM, new GymPricing());
    }
    
    public static RoomPricingStrategy getRoomPricing(int roomType) {
        return roomPricingMap.getOrDefault(roomType, new DeluxeRoomPricing());
    }
    
    public static AddOnPricing getAddOnPricing(AddOn addOn) {
        return addOnPricingMap.get(addOn);
    }
}
