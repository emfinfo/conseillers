package filters;

import javax.inject.Inject;
import play.filters.gzip.GzipFilter;
import play.http.HttpFilters;
import play.mvc.EssentialFilter;

/**
 * Filtre Play pour utiliser GZip.
 *
 * @author jcstritt
 */
//public class Filters implements HttpFilters {
//
//  @Inject
//  GzipFilter gzipFilter;
//
//  @Inject
//  CORSFilter corsFilter;
//
//  @Override
//  public EssentialFilter[] filters() {
//    return new EssentialFilter[]{gzipFilter, corsFilter};
//  }
//
//}
public class Filters implements HttpFilters {

  private EssentialFilter[] filters;

  @Inject
  public Filters(GzipFilter gzipFilter) {
    filters = new EssentialFilter[]{gzipFilter.asJava()};
  }

  @Override
  public EssentialFilter[] filters() {
    return filters;
  }
}
