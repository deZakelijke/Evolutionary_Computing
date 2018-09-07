package org.vu.contest;

import java.util.Properties;

public abstract interface ContestEvaluation
{
  public abstract Object evaluate(Object paramObject);
  
  public abstract Object getData(Object paramObject);
  
  public abstract double getFinalResult();
  
  public abstract Properties getProperties();
}
