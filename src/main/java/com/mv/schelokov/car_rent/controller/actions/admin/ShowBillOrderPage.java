package com.mv.schelokov.car_rent.controller.actions.admin;

import com.mv.schelokov.car_rent.controller.actions.AbstractAction;
import com.mv.schelokov.car_rent.controller.actions.JspForward;
import com.mv.schelokov.car_rent.controller.consts.Jsps;
import com.mv.schelokov.car_rent.controller.exceptions.ActionException;
import com.mv.schelokov.car_rent.model.entities.Invoice;
import com.mv.schelokov.car_rent.model.entities.RentOrder;
import com.mv.schelokov.car_rent.model.services.CarService;
import com.mv.schelokov.car_rent.model.services.InvoiceService;
import com.mv.schelokov.car_rent.model.services.OrderService;
import com.mv.schelokov.car_rent.model.services.UserService;
import com.mv.schelokov.car_rent.model.services.exceptions.ServiceException;
import com.mv.schelokov.car_rent.model.utils.DateUtils;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Maxim Chshelokov <schelokov.mv@gmail.com>
 */
public class ShowBillOrderPage extends AbstractAction {
    private static final Logger LOG = Logger.getLogger(ShowBillOrderPage.class);
    private static final String ERROR = "Failed to get data from database";

    @Override
    public JspForward execute(HttpServletRequest req, HttpServletResponse res)
            throws ActionException {
        if (isAdmin(req)) {
            int orderId = getIntParam(req, "id");
            if (orderId < 1)
                throw new ActionException("Incorrect order id");
            try {
                RentOrder order = OrderService.getOrderById(orderId);
                InvoiceService.recalculateInvoice(order);
                
                req.setAttribute("user_data",
                        UserService.getUserDataById(order.getUser().getId()));
                req.setAttribute("car",
                        CarService.getCarById(order.getCar().getId()));
                
                Invoice invoice = InvoiceService.getInvoiceById(order.getId());
                req.setAttribute("invoice", invoice);
                
                req.setAttribute("invoice_lines", InvoiceService
                        .getInvoiceLinesByInvoiceId(invoice.getId()));

                return new JspForward(Jsps.ADMIN_BILL_ORDER);

            } catch (ServiceException ex) {
                LOG.error(ERROR, ex);
                throw new ActionException(ERROR, ex);
            }
        } else {
            sendForbidden(res);
            return null;
        }
    }
}
