package filters;

import play.api.mvc.EssentialFilter;
import play.filters.gzip.GzipFilter;
import play.http.HttpFilters;
import javax.inject.Inject;

/**
 *
 * @author jcstritt
 */
public class Filters implements HttpFilters {
  @Inject
  GzipFilter gzipFilter;

  @Override
  public EssentialFilter[] filters() {
    return new EssentialFilter[]{gzipFilter};
  }
}
