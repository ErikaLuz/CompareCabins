package exception;

public class CCException extends Exception
{
    private static final long serialVersionUID = 1L;

    public CCException( String msg )
    {
      super( msg );
    }

    public CCException( Throwable cause )
    {
      super( cause );
    }
}
