package by.persons.navigation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


public class PageSplitter<T> {
    private List<T> items = new ArrayList<>();
    private long currentPage;
    private int subPagesQuantity;
    private int currentSubPage;
    private int quantityLinesPerPage;
    public final String PARAM_ITEMS;
    public final String PARAM_CURRENT_PAGE;
    public final String PARAM_LINES_PER_PAGE;
    public static final String PARAM_CURRENT_SUB_PAGE = "currentSubPage";
    public static final String PARAM_QUANTITY_LINES_PER_PAGE = "quantityLinesPerPage";
    public static final String PARAM_SUB_PAGES_QUANTITY = "subPageQuantity";
    public static final String PARAM_PERSONS_SUB_LIST = "personsSubList";
    public static final String PARAM_IS_LAST_PAGE = "isLastPage";
    public static final int INIT_CURRENT_SUB_PAGE_VALUE = 0;
    public static final int INIT_QUANTITY_LINES_PER_PAGE_VALUE = 5;
    public static final long FIRST_PAGE = 1;

    public PageSplitter(String PARAM_ITEMS, String PARAM_LINES_PER_PAGE, String PARAM_CURRENT_PAGE) {
        this.PARAM_ITEMS = PARAM_ITEMS;
        this.PARAM_LINES_PER_PAGE = PARAM_LINES_PER_PAGE;
        this.PARAM_CURRENT_PAGE = PARAM_CURRENT_PAGE;
        currentSubPage = INIT_CURRENT_SUB_PAGE_VALUE;
        quantityLinesPerPage = INIT_QUANTITY_LINES_PER_PAGE_VALUE;
        currentPage = FIRST_PAGE;
    }


    public void obtainParametersFromSession(HttpSession session) {
        items = (List) session.getAttribute(PARAM_ITEMS);
        currentPage = (long) session.getAttribute(PARAM_CURRENT_PAGE);
        subPagesQuantity = (int) session.getAttribute(PARAM_SUB_PAGES_QUANTITY);
        currentSubPage = (int) session.getAttribute(PARAM_CURRENT_SUB_PAGE);
        quantityLinesPerPage = (int) session.getAttribute(PARAM_QUANTITY_LINES_PER_PAGE);
    }

    public void importInitialParametersToSession(HttpSession session) {
        session.setAttribute(PARAM_SUB_PAGES_QUANTITY, calculatePageQuantity());
        session.setAttribute(PARAM_ITEMS, items);
        session.setAttribute(PARAM_CURRENT_SUB_PAGE, INIT_CURRENT_SUB_PAGE_VALUE);
        session.setAttribute(PARAM_CURRENT_PAGE, FIRST_PAGE);
        session.setAttribute(PARAM_QUANTITY_LINES_PER_PAGE, INIT_QUANTITY_LINES_PER_PAGE_VALUE);
    }

    public void importCurrentParametersToSession(HttpSession session) {
        session.setAttribute(PARAM_SUB_PAGES_QUANTITY, calculatePageQuantity());
        session.setAttribute(PARAM_ITEMS, items);
        session.setAttribute(PARAM_CURRENT_SUB_PAGE, currentSubPage);
        session.setAttribute(PARAM_CURRENT_PAGE, currentPage);
        session.setAttribute(PARAM_QUANTITY_LINES_PER_PAGE, quantityLinesPerPage);
    }

    public boolean isLastPage() {
        return currentSubPage >= subPagesQuantity;
    }

    public void obtainQuantityLinesPerPageFromRequest(HttpServletRequest request) {
        String linesPerPage = request.getParameter(PARAM_LINES_PER_PAGE);
        if (linesPerPage != null) {
            quantityLinesPerPage = Integer.parseInt(linesPerPage);
        } else {
            quantityLinesPerPage = INIT_QUANTITY_LINES_PER_PAGE_VALUE;
        }
    }

    public void obtainQuantityLinesPerPageFromSession(HttpSession session) {
        quantityLinesPerPage = (int) session.getAttribute(PARAM_QUANTITY_LINES_PER_PAGE);
    }

    public void importCurrentSubPageToSession(HttpSession session) {
        session.setAttribute(PARAM_CURRENT_SUB_PAGE, currentSubPage);
    }

    public void importInitialCurrentSubPageToSession(HttpSession session, int offset) {
        session.setAttribute(PARAM_CURRENT_SUB_PAGE, INIT_CURRENT_SUB_PAGE_VALUE + offset);
    }

    public void importItemsToSession(HttpSession session) {
        session.setAttribute(PARAM_ITEMS, items);
    }

    public void importSubPageQuantityToSession(HttpSession session) {
        session.setAttribute(PARAM_SUB_PAGES_QUANTITY, subPagesQuantity);
    }

    public void importFirstItemsSubListToRequest(HttpServletRequest request, HttpSession session) {
        if (items.size() >= quantityLinesPerPage) {
            request.setAttribute(PARAM_PERSONS_SUB_LIST, items.subList(0, quantityLinesPerPage));
        } else {
            session.setAttribute(PARAM_IS_LAST_PAGE, true);
            request.setAttribute(PARAM_PERSONS_SUB_LIST, items.subList(0, items.size()));
        }
    }

    public void importItemsSubListToRequest(HttpServletRequest request, HttpSession session) {
        int startPosition = currentSubPage * quantityLinesPerPage;
        int remainedLines = items.size() - startPosition;
        List subPersons = quantityLinesPerPage >= remainedLines ? items.subList(startPosition, startPosition + remainedLines)
                : items.subList(startPosition, startPosition + quantityLinesPerPage);
        currentSubPage++;
        if ((subPersons.size() == remainedLines) && (subPersons.size() < quantityLinesPerPage)) {
            session.setAttribute(PARAM_IS_LAST_PAGE, true);
            request.setAttribute(PARAM_PERSONS_SUB_LIST, items.subList(startPosition, items.size()));
        } else {
            session.setAttribute(PARAM_CURRENT_SUB_PAGE, currentSubPage);
            request.setAttribute(PARAM_PERSONS_SUB_LIST, subPersons);
        }
    }

    public int calculatePageQuantity() {
        int pageQuantity = items.size() / quantityLinesPerPage;
        return items.size() % quantityLinesPerPage != 0 ? ++pageQuantity : pageQuantity;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items.clear();
        this.items.addAll(items);
        subPagesQuantity = calculatePageQuantity();
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getSubPagesQuantity() {
        return subPagesQuantity;
    }

    public void setSubPagesQuantity(int subPagesQuantity) {
        this.subPagesQuantity = subPagesQuantity;
    }

    public int getCurrentSubPage() {
        return currentSubPage;
    }

    public void setCurrentSubPage(int currentSubPage) {
        this.currentSubPage = currentSubPage;
    }

    public int getQuantityLinesPerPage() {
        return quantityLinesPerPage;
    }

    public void setQuantityLinesPerPage(int quantityLinesPerPage) {
        this.quantityLinesPerPage = quantityLinesPerPage;
    }
}
