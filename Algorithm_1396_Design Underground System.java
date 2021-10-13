import java.util.HashMap;
import java.util.Map;

// 方法：timeInfoMap可以看做一张二维表，行是startStation，列是endStation，记录从startStation上车
// 到endStation下车的所有旅程的总时间和旅程数
// 对于已经checkin但还未checkout的乘客，放在passengerMap中
// 有新乘客checkin，就将其放入passengerMap
// 有乘客checkout，就从passengerMap中取出其信息，记录其完整的旅程总时长，然后放入timeInfoMap
// 调用getAverageTime时，就可以直接从timeInfoMap中根据start和end station取出对应的timeInfo，计算平均时间

class UndergroundSystem {

    class TimeInfo {
        public int totalTime;
        public int count;
        public TimeInfo(int time) {
            this.totalTime = time;
            this.count = 1;
        }
    }

    class PassengerInfo {
        public int id;
        public String startStation;
        public int checkInTime;
        public PassengerInfo(int id, String startStation, int checkInTime) {
            this.id = id;
            this.startStation = startStation;
            this.checkInTime = checkInTime;
        }
    }

    private Map<String, Map<String, TimeInfo>> timeInfoMap;
    private Map<Integer, PassengerInfo> passengerMap;

    public UndergroundSystem() {
        timeInfoMap = new HashMap<>();
        passengerMap = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        PassengerInfo passengerInfo = new PassengerInfo(id, stationName, t);
        passengerMap.put(id, passengerInfo);
    }
    
    public void checkOut(int id, String stationName, int t) {
        PassengerInfo passengerInfo = passengerMap.get(id);
        int timeElapsed = t - passengerInfo.checkInTime;
        String startStation = passengerInfo.startStation;
        String endStation = stationName;
        if (!timeInfoMap.containsKey(startStation)) {
            timeInfoMap.put(startStation, new HashMap<>());
        }
        TimeInfo timeInfo = null;
        if (!timeInfoMap.get(startStation).containsKey(endStation)) {
            timeInfo = new TimeInfo(timeElapsed);
        } else {
            timeInfo = timeInfoMap.get(startStation).get(endStation);
            timeInfo.totalTime += timeElapsed;
            timeInfo.count++;
        }
        timeInfoMap.get(startStation).put(endStation, timeInfo);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        TimeInfo timeInfo = timeInfoMap.get(startStation).get(endStation);
        return ((double) timeInfo.totalTime) / timeInfo.count;
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */