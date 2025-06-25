package toy.db.master_slave_test.global.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 *   <ul> AbstractRoutingDataSource 를 상속하여 </ul>
 *   <ul> 현재 요청이 ReadOnly 트랜잭션인지 여부에 따라 </ul>
 *   <ul> Master Slave 분기 </ul>
 */
@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {

  @Override
  protected Object determineCurrentLookupKey() {
//    String key = (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) ? "slave" : "master";
//    log.info("RoutingDataSource detected: {}", key);
    return (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) ? "slave" : "master";
  }
}
