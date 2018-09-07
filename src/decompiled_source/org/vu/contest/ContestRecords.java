package org.vu.contest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;











public class ContestRecords
  implements Serializable
{
  private static final long serialVersionUID = 1578987923L;
  private HashMap<String, Vector<ContestEntry>> records;
  private long lastUpdate;
  
  public ContestRecords()
  {
    records = new HashMap();
    lastUpdate = 0L;
  }
  









  public void addEntry(ContestEntry paramContestEntry)
  {
    String str = paramContestEntry.getPlayer();
    
    Vector localVector = (Vector)records.get(str);
    if (localVector == null) {
      localVector = new Vector();
      records.put(str, localVector);
    }
    
    localVector.add(paramContestEntry);
    

    lastUpdate = paramContestEntry.getTime();
  }
  




  public void addEntries(Vector<ContestEntry> paramVector)
  {
    for (int i = 0; i < paramVector.size(); i++) { addEntry((ContestEntry)paramVector.get(i));
    }
  }
  




  public long getLastUpdate()
  {
    return lastUpdate;
  }
  









  public Vector<ContestEntry> getRankings(String paramString)
  {
    Vector localVector = new Vector();
    Iterator localIterator = records.keySet().iterator();
    while (localIterator.hasNext()) {
      ContestEntry localContestEntry = (ContestEntry)((Vector)records.get(localIterator.next())).lastElement();
      if (localContestEntry.getScore(paramString) != null) localVector.add(localContestEntry);
    }
    Collections.sort(localVector, new EntryComparator(paramString));
    return localVector;
  }
  






  public static void storeRecord(ContestRecords paramContestRecords, String paramString)
    throws IOException, FileNotFoundException
  {
    ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(new FileOutputStream(paramString));
    localObjectOutputStream.writeObject(paramContestRecords);
  }
  








  public static ContestRecords loadRecord(String paramString)
    throws InvalidObjectException, FileNotFoundException, IOException, ClassNotFoundException
  {
    ObjectInputStream localObjectInputStream = new ObjectInputStream(new FileInputStream(paramString));
    Object localObject = localObjectInputStream.readObject();
    

    if ((localObject instanceof ContestRecords)) return (ContestRecords)localObject;
    throw new InvalidObjectException("File does not contain a valid ContestRecords object");
  }
}
