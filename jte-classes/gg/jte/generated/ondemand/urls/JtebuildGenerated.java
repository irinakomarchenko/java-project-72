package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.BasePage;
import hexlet.code.util.NamedRoutes;
import hexlet.code.dto.BasePage;
import org.apache.commons.lang3.StringUtils;
@SuppressWarnings("unchecked")
public final class JtebuildGenerated {
	public static final String JTE_NAME = "urls/build.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,4,4,4,4,6,6,8,8,14,14,18,18,18,22,22,30,30,30,30,30,30,30,30,30,48,48,48,48,48,4,4,4,4};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, BasePage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <svg xmlns=\"http://www.w3.org/2000/svg\" class=\"d-none\">\r\n        <symbol id=\"exclamation-triangle-fill\" viewBox=\"0 0 16 16\">\r\n            <path d=\"M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z\"/>\r\n        </symbol>\r\n    </svg>\r\n    ");
				if (StringUtils.contains(page.getMessage(), "Некорректный URL")) {
					jteOutput.writeContent("\r\n        <div class=\"alert alert-danger d-flex align-items-center alert-dismissible fade show m-0\" role=\"alert\">\r\n            <svg class=\"bi flex-shrink-0 me-2\" role=\"img\" aria-label=\"Danger:\"><use xlink:href=\"#exclamation-triangle-fill\"/></svg>\r\n            <div>\r\n                ");
					jteOutput.setContext("div", null);
					jteOutput.writeUserContent(page.getMessage());
					jteOutput.writeContent("\r\n            </div>\r\n            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\r\n        </div>\r\n    ");
				}
				jteOutput.writeContent("\r\n\r\n    <section>\r\n        <div class=\"container-fluid bg-dark p-5\">\r\n            <div class=\"row\">\r\n                <div class=\"col-md-10 col-lg-8 mx-auto text-white\">\r\n                    <h1 class=\"display-4 mb-0\">Page Analyzer</h1>\r\n                    <p class=\"lead fw-lighter\">Бесплатно проверяйте сайты на SEO пригодность</p>\r\n                    <form");
				var __jte_html_attribute_0 = NamedRoutes.urlsPath();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" method=\"post\" class=\"rss-form text-muted\">\r\n                        <div class=\"row\">\r\n                            <div class=\"col\">\r\n                                <div class=\"form-floating\">\r\n                                    <input id=\"url-input\" autofocus=\"\" type=\"text\" required=\"\" name=\"url\" aria-label=\"url\" class=\"form-control w-100\" placeholder=\"ссылка RSS\" autocomplete=\"off\">\r\n                                    <label for=\"url-input\">Ссылка</label>\r\n                                </div>\r\n                            </div>\r\n                            <div class=\"col-auto\">\r\n                                <button type=\"submit\" class=\"h-100 btn btn-lg btn-success opacity-75 px-sm-5\">Проверить</button>\r\n                            </div>\r\n                        </div>\r\n                    </form>\r\n                    <p class=\"opacity-50 mt-2 mb-0\">Пример: https://www.example.com</p>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </section>\r\n");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		BasePage page = (BasePage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
