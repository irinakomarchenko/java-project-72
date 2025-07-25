@import hexlet.code.dto.urls.UrlPage
@import hexlet.code.util.NamedRoutes
@import org.apache.commons.lang3.StringUtils
@import java.time.format.DateTimeFormatter
@param UrlPage page

@template.layout.page(
content = @`
    <svg xmlns="http://www.w3.org/2000/svg" class="d-none">
        <symbol id="exclamation-triangle-fill" viewBox="0 0 16 16">
            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </symbol>
    </svg>
    @if(page.getMessage() != null)
        @if(page.getMessage().contains("не удалось получить все данные"))
            <div class="alert alert-warning d-flex align-items-center alert-dismissible fade show m-0" role="alert">
                <svg class="bi flex-shrink-0 me-2" role="img" aria-label="Warning:">
                    <use xlink:href="#exclamation-triangle-fill" />
                </svg>
                <div>${page.getMessage()}</div>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        @elseif(page.getMessage().contains("успешно"))
            <div class="alert alert-success d-flex align-items-center alert-dismissible fade show m-0" role="alert">
                <svg class="bi flex-shrink-0 me-2" role="img" aria-label="Success:">
                    <use xlink:href="#exclamation-triangle-fill" />
                </svg>
                <div>${page.getMessage()}</div>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        @else
            <div class="alert alert-danger d-flex align-items-center alert-dismissible fade show m-0" role="alert">
                <svg class="bi flex-shrink-0 me-2" role="img" aria-label="Danger:">
                    <use xlink:href="#exclamation-triangle-fill" />
                </svg>
                <div>${page.getMessage()}</div>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        @endif
    @endif
        <div class="mx-4 mt-4 mb-0">
        <div class="flex-row fs-6">
            <table class="table table-light table-hover table-bordered border-white border-4 caption-top table-responsive">
                <caption class="fs-4">Сайт <span class="fw-light fst-italic">${page.getUrl().getName()}</span></caption>
                <tbody class="m-2">
                <tr>
                    <th scope="row">ID</th>
                    <td>${page.getUrl().getId()}</td>
                </tr>
                <tr>
                    <th scope="row">Название</th>
                    <td>${page.getUrl().getName()}</td>
                </tr>
                <tr>
                    <th scope="row">Дата добавления</th>
                    <td>${page.getUrl().getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"))}</td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="d-flex py-3">
            <form action="${NamedRoutes.urlCheckPath(page.getUrl().getId())}" method="post" class="rss-form">
                <button type="submit" class="h-100 btn btn-lg btn-success opacity-75 px-sm-5">Запустить проверку</button>
            </form>
        </div>

        !{var checks = page.getChecks();}
        @if(!checks.isEmpty())
            <div class="flex-row fs-6 mt-2">
                <table class="table table-light table-hover table-bordered border-white border-4 caption-top">
                    <caption class="fs-4">Проверки</caption>
                    <thead>
                    <tr>
                        <th class="col-1" scope="col">ID</th>
                        <th class="col-1" scope="col">Код ответа</th>
                        <th class="col-2" scope="col">h1</th>
                        <th class="col-2" scope="col">title</th>
                        <th class="col-auto" scope="col">description</th>
                        <th class="col-2" scope="col">Дата проверки</th>
                    </tr>
                    </thead>
                    <tbody>
                    @for(var check : page.getChecks())
                        <tr>
                            <td>${check.getId()}</td>
                            <td>${check.getStatusCode()}</td>
                            <td>${check.getH1()}</td>
                            <td>${check.getTitle()}</td>
                            <td>${check.getDescription()}</td>
                            <td>${check.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"))}</td>
                        </tr>
                    @endfor
                    </tbody>
                </table>
            </div>
        @endif
    </div>
`
)