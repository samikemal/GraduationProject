alert("test")

$(".addBasket").click(function (el) {
    var id = $(el).data().id;
    $.poster("controller/request", { urunid :  id},function () {

    }).done(function () {

    }).error(function () {

    })
})
