package gg.jte.generated.ondemand.urls;
import hexlet.code.model.Url;
import hexlet.code.util.NamedRoutes;
import hexlet.code.dto.urls.UrlsPage;
import org.apache.commons.lang3.StringUtils;
import java.text.SimpleDateFormat;
@SuppressWarnings("unchecked")
public final class JteindexGenerated {
	public static final String JTE_NAME = "urls/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,4,5,5,5,5,7,7,9,9,18,18,22,22,22,26,26,30,30,30,34,34,38,38,41,41,45,45,58,58,60,60,60,61,61,61,61,61,61,61,61,61,61,61,61,63,63,64,64,65,65,65,66,66,66,67,67,70,70,72,72,79,79,80,80,80,80,80,80,80,80,80,80,80,80,81,81,84,84,88,88,88,88,88,5,5,5,5};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <svg xmlns=\"http://www.w3.org/2000/svg\" class=\"d-none\">\r\n        <symbol id=\"check-circle-fill\" viewBox=\"0 0 16 16\">\r\n            <path d=\"M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z\"/>\r\n        </symbol>\r\n        <symbol id=\"exclamation-triangle-fill\" viewBox=\"0 0 16 16\">\r\n            <path d=\"M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z\"/>\r\n        </symbol>\r\n    </svg>\r\n    ");
				if (StringUtils.contains(page.getMessage(), "Страница успешно добавлена")) {
					jteOutput.writeContent("\r\n        <div class=\"alert alert-success d-flex align-items-center alert-dismissible fade show m-0\" role=\"alert\">\r\n            <svg class=\"bi flex-shrink-0 me-2\" role=\"img\" aria-label=\"Success:\"><use xlink:href=\"#check-circle-fill\"/></svg>\r\n            <div>\r\n                ");
					jteOutput.setContext("div", null);
					jteOutput.writeUserContent(page.getMessage());
					jteOutput.writeContent("\r\n            </div>\r\n            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\r\n        </div>\r\n    ");
				} else if (StringUtils.contains(page.getMessage(), "Страница уже существует")) {
					jteOutput.writeContent("\r\n        <div class=\"alert alert-warning d-flex align-items-center alert-dismissible fade show m-0\" role=\"alert\">\r\n            <svg class=\"bi flex-shrink-0 me-2\" role=\"img\" aria-label=\"Danger:\"><use xlink:href=\"#exclamation-triangle-fill\"/></svg>\r\n            <div>\r\n                ");
					jteOutput.setContext("div", null);
					jteOutput.writeUserContent(page.getMessage());
					jteOutput.writeContent("\r\n            </div>\r\n            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\r\n        </div>\r\n    ");
				}
				jteOutput.writeContent("\r\n\r\n    <section>\r\n        <div class=\"container-lg mt-5\">\r\n            ");
				var urls = page.getUrls();
				jteOutput.writeContent("\r\n\r\n            <div class=\"mx-4 mt-4 mb-0\">\r\n                ");
				if (urls.isEmpty()) {
					jteOutput.writeContent("\r\n                    <div class=\"fs-4\">\r\n                        The list is empty\r\n                    </div>\r\n                ");
				} else {
					jteOutput.writeContent("\r\n                    <div class=\"container-lg mt-5 table-responsive\">\r\n                        <table class=\"table table-light table-hover table-bordered border-white border-4 caption-top\">\r\n                            <caption class=\"fs-3 fw-bolder\">Сайты</caption>\r\n                            <thead class=\"align-middle\">\r\n                            <tr>\r\n                                <th class=\"col-1\">ID</th>\r\n                                <th>Название</th>\r\n                                <th class=\"col-2\">Последняя проверка</th>\r\n                                <th class=\"col-2\">Код ответа</th>\r\n                            </tr>\r\n                            </thead>\r\n                            <tbody>\r\n                            ");
					for (Url item : urls) {
						jteOutput.writeContent("\r\n                                <tr>\r\n                                    <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(item.getId());
						jteOutput.writeContent("</td>\r\n                                    <td><a");
						var __jte_html_attribute_0 = NamedRoutes.urlPath(item.getId());
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
							jteOutput.writeContent(" href=\"");
							jteOutput.setContext("a", "href");
							jteOutput.writeUserContent(__jte_html_attribute_0);
							jteOutput.setContext("a", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">");
						jteOutput.setContext("a", null);
						jteOutput.writeUserContent(item.getName());
						jteOutput.writeContent("</a></td>\r\n\r\n                                    ");
						var latestCheck = page.getChecks().get(item);
						jteOutput.writeContent("\r\n                                    ");
						if (latestCheck != null) {
							jteOutput.writeContent("\r\n                                        <td>");
							jteOutput.setContext("td", null);
							jteOutput.writeUserContent(new SimpleDateFormat("dd/MM/yy HH:mm").format(latestCheck.getCreatedAt()));
							jteOutput.writeContent("</td>\r\n                                        <td>");
							jteOutput.setContext("td", null);
							jteOutput.writeUserContent(latestCheck.getStatusCode());
							jteOutput.writeContent("</td>\r\n                                    ");
						} else {
							jteOutput.writeContent("\r\n                                        <td></td>\r\n                                        <td></td>\r\n                                    ");
						}
						jteOutput.writeContent("\r\n                                </tr>\r\n                            ");
					}
					jteOutput.writeContent("\r\n                            </tbody>\r\n                        </table>\r\n                    </div>\r\n\r\n                    <div class=\"btn-toolbar position-relative text-light py-6 d-flex justify-content-center\" role=\"toolbar\" aria-label=\"Toolbar with button groups\">\r\n                        <div class=\"btn-group me-2\" role=\"group\" aria-label=\"First group\">\r\n                            ");
					for (int i = 1; i <= page.getPageNumber(); i++) {
						jteOutput.writeContent("\r\n                                <a class=\"btn btn-success opacity-75\"");
						var __jte_html_attribute_1 = NamedRoutes.urlsPath(i);
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
							jteOutput.writeContent(" href=\"");
							jteOutput.setContext("a", "href");
							jteOutput.writeUserContent(__jte_html_attribute_1);
							jteOutput.setContext("a", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(" role=\"button\">");
						jteOutput.setContext("a", null);
						jteOutput.writeUserContent(i);
						jteOutput.writeContent("</a>\r\n                            ");
					}
					jteOutput.writeContent("\r\n                        </div>\r\n                    </div>\r\n                ");
				}
				jteOutput.writeContent("\r\n            </div>\r\n        </div>\r\n    </section>\r\n");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
