import java.util.Properties;
import javabbob.JNIfgeneric;
import javabbob.JNIfgeneric.Params;
import org.vu.contest.ContestEvaluation;

public class KatsuuraEvaluation
  implements ContestEvaluation
{
  private static final int EVALS_LIMIT_ = 1000000;
  private static final int bbobid_ = 23;
  private static final double BASE_ = 7.73838444D;
  private JNIfgeneric function_ = null;
  private double best_;
  private double target_ = 0.0D;
  
  private int evaluations_;
  private String multimodal_ = "true";
  private String regular_ = "false";
  private String separable_ = "false";
  private String evals_ = Integer.toString(1000000);
  
  public KatsuuraEvaluation()
  {
    best_ = 0.0D;
    
    evaluations_ = 0;
    
    function_ = new JNIfgeneric();
    JNIfgeneric.Params localParams = new JNIfgeneric.Params();
    algName = "";
    comments = "";
    JNIfgeneric.makeBBOBdirs("tmp", true);
    function_.initBBOB(23, 1, 10, "tmp", localParams);
    target_ = function_.getFtarget();
  }
  


  public Object evaluate(Object paramObject)
  {
    if (!(paramObject instanceof double[])) throw new IllegalArgumentException();
    double[] arrayOfDouble = (double[])paramObject;
    if (arrayOfDouble.length != 10) { throw new IllegalArgumentException();
    }
    if (evaluations_ > 1000000) { return null;
    }
    double d1 = (function_.evaluate(arrayOfDouble) - target_) / (7.73838444D - target_);
    double d2 = 10.0D * Math.exp(-5.0D * d1);
    if (d2 > 10.0D) d2 = 10.0D; else if (d2 < 0.0D) d2 = 0.0D;
    if (d2 > best_) best_ = d2;
    evaluations_ += 1;
    
    return new Double(d2);
  }
  

  public Object getData(Object paramObject)
  {
    return null;
  }
  

  public double getFinalResult()
  {
    return best_;
  }
  

  public Properties getProperties()
  {
    Properties localProperties = new Properties();
    localProperties.put("Multimodal", multimodal_);
    localProperties.put("Regular", regular_);
    localProperties.put("Separable", separable_);
    localProperties.put("Evaluations", evals_);
    return localProperties;
  }
}
