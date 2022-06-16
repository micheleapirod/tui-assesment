package com.tui.proof.command;

import com.tui.proof.domain.OrderState;
import com.tui.proof.domain.PendingOrders;
import com.tui.proof.dto.TuiOrderDto;
import com.tui.proof.service.TuiOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@Slf4j
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class OrderCommand {

    private final TuiOrderService tuiOrderService;

    /**
     * @param tuiOrderService
     */
    @Autowired
    public OrderCommand(TuiOrderService tuiOrderService){
        this.tuiOrderService = tuiOrderService;
    }

    /**
     * @param tuiOrderDto
     * @return
     */
    public TuiOrderDto execute(TuiOrderDto tuiOrderDto) {
        boolean orderToUpdate = false;
        if(StringUtils.isEmpty(tuiOrderDto.getUuid())) {
            String uuid = UUID.randomUUID().toString();
            PendingOrders.ordersInPending().put(uuid, tuiOrderDto);
            tuiOrderDto.setOrderState(OrderState.PENDING);
            tuiOrderDto.setMessage("Order has been generated");
            tuiOrderDto.setUuid(uuid);
            log.info("An order is placed in queue");
            orderToUpdate = true;
        } else {
            boolean isOrderPresent = PendingOrders.ordersInPending().containsKey(tuiOrderDto.getUuid());
            if(!isOrderPresent) {
                tuiOrderDto.setMessage("The order is not present in queue");
                tuiOrderDto.setOrderState(OrderState.COMPLETED);
            } else {
                tuiOrderDto.setMessage("The order is in progress");
                tuiOrderDto.setOrderState(OrderState.IN_PROGRESS);
                PendingOrders.ordersInPending().put(tuiOrderDto.getUuid(), tuiOrderDto);
            }
        }

        String finalUuid = tuiOrderDto.getUuid();

        if(orderToUpdate) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    tuiOrderService.save(PendingOrders.ordersInPending().get(finalUuid));
                    PendingOrders.ordersInPending().remove(finalUuid);
                }
            }, 300000);
        }
        return tuiOrderDto;
    }
}
