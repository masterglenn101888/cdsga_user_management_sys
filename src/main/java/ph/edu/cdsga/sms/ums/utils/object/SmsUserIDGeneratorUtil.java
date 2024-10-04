package ph.edu.cdsga.sms.ums.utils.object;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;
import ph.edu.cdsga.sms.ums.entity.account.SmsUser;

import java.io.Serializable;

public class SmsUserIDGeneratorUtil extends UUIDGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        if (obj == null) throw new HibernateException(new NullPointerException()) ;

        if ((((SmsUser) obj).getUserId()) == null) {
            return super.generate(session, obj) ;
        } else {
            return ((SmsUser) obj).getUserId();
        }
    }
}