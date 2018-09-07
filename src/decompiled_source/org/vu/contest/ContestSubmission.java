package org.vu.contest;

public abstract interface ContestSubmission
{
  public abstract void setEvaluation(ContestEvaluation paramContestEvaluation);
  
  public abstract void setSeed(long paramLong);
  
  public abstract void run();
}
