package org.vu.contest;

import java.util.Comparator;

public class EntryComparator implements Comparator<ContestEntry>
{
  private String evaluation;
  
  public EntryComparator(String paramString)
  {
    evaluation = paramString;
  }
  
  public String getEvaluation()
  {
    return evaluation;
  }
  
  public int compare(ContestEntry paramContestEntry1, ContestEntry paramContestEntry2)
  {
    Double localDouble1 = paramContestEntry1.getScore(evaluation);
    Double localDouble2 = paramContestEntry2.getScore(evaluation);
    if ((localDouble1 == null) && (localDouble2 == null)) return 0;
    if (localDouble2 == null) return 1;
    if (localDouble1 == null) return -1;
    if (localDouble1.doubleValue() < localDouble2.doubleValue()) return -1;
    if (localDouble1.doubleValue() > localDouble2.doubleValue()) return 1;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (((EntryComparator)paramObject).getEvaluation().equals(evaluation)) return true;
    return false;
  }
}
