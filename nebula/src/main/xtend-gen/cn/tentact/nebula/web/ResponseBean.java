package cn.tentact.nebula.web;

import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * 网络相应JSON实体类
 */
@Data
@SuppressWarnings("all")
public class ResponseBean {
  private final int code;
  
  private final String msg;
  
  private final boolean result;
  
  private final Object data;
  
  public ResponseBean(final int code, final String msg, final boolean result, final Object data) {
    super();
    this.code = code;
    this.msg = msg;
    this.result = result;
    this.data = data;
  }
  
  @Override
  @Pure
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + this.code;
    result = prime * result + ((this.msg== null) ? 0 : this.msg.hashCode());
    result = prime * result + (this.result ? 1231 : 1237);
    result = prime * result + ((this.data== null) ? 0 : this.data.hashCode());
    return result;
  }
  
  @Override
  @Pure
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ResponseBean other = (ResponseBean) obj;
    if (other.code != this.code)
      return false;
    if (this.msg == null) {
      if (other.msg != null)
        return false;
    } else if (!this.msg.equals(other.msg))
      return false;
    if (other.result != this.result)
      return false;
    if (this.data == null) {
      if (other.data != null)
        return false;
    } else if (!this.data.equals(other.data))
      return false;
    return true;
  }
  
  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("code", this.code);
    b.add("msg", this.msg);
    b.add("result", this.result);
    b.add("data", this.data);
    return b.toString();
  }
  
  @Pure
  public int getCode() {
    return this.code;
  }
  
  @Pure
  public String getMsg() {
    return this.msg;
  }
  
  @Pure
  public boolean isResult() {
    return this.result;
  }
  
  @Pure
  public Object getData() {
    return this.data;
  }
}
