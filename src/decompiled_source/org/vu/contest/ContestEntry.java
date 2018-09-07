package org.vu.contest;

import java.io.Serializable;
import java.util.HashMap;





public class ContestEntry
  implements Comparable, Serializable
{
  private static final long serialVersionUID = 1472883827L;
  private String player;
  private HashMap<String, Double> evalScores;
  private long time;
  
  public ContestEntry() {}
  
  public ContestEntry(String paramString, long paramLong)
  {
    player = paramString;
    time = paramLong;
    evalScores = new HashMap();
  }
  
  public void setScore(String paramString, double paramDouble)
  {
    evalScores.put(paramString, new Double(paramDouble));
  }
  
  public String getPlayer() {
    return player;
  }
  
  public long getTime() {
    return time;
  }
  
  public Double getScore(String paramString) {
    return (Double)evalScores.get(paramString);
  }
  
  public int compareTo(Object paramObject)
  {
    long l = ((ContestEntry)paramObject).getTime();
    if (time < l) return -1;
    if (time > l) return 1;
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == null) return false;
    if (!(paramObject instanceof ContestEntry)) return false;
    if ((player.equals(((ContestEntry)paramObject).getPlayer())) && (time == ((ContestEntry)paramObject).getTime())) return true;
    return false;
  }
}
