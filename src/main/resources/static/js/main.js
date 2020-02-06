$(function(){
    const appendDeal = function(data){
        var dealCode = '<a href="#" class="deal-link" data-id="' +
            data.id + '">' + data.name + '</a>  ';
        var dealRemoveImg =
        '<img src="https://ru.wargaming.net/clans/media/clans/emblems/cl_555/205555/emblem_64x64.png" width="34px" data-id="'
            + data.id + '" class="delete-deal">';
        var dealChangeImg =
        '<img src="https://www.riccardofratini.it/wp-content/uploads/2019/02/icons8-edit-64.png" width="34px" data-id="'
            + data.id + '" class="deal-change">';
        $('#deal-list')
            .append('<div>' + dealCode
            + dealRemoveImg
            + dealChangeImg
            + '</div><br>');
    };

//    Loading books on load page
//    $.get('/books/', function(response)
//    {
//        for(i in response) {
//            appendBook(response[i]);
//        }
//    });

    //Show adding deal form
    $('#show-add-deal-form').click(function(){
        $('#deal-form').css('display', 'flex');
    });

    //Closing adding deal form
    $('#deal-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting deal
    $(document).on('click', '.deal-link', function(){
        var link = $(this);
        var dealId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/deals/' + dealId,
            success: function(response)
            {
                var code = '<br><span>Дата: ' + response.date + '</span><br>';
                link.parent().append(code);
                $(link).css('pointer-events', 'none');
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Такая задача не найдена!');
                }
            }
        });
        return false;
    });

    //Adding deal
    $('#save-deal').click(function()
    {
        var data = $('#deal-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/deals/',
            data: data,
            success: function(response)
            {
                $('#deal-form').css('display', 'none');
                var deal = {};
                deal.id = response;
                var dataArray = $('#deal-form form').serializeArray();
                for(i in dataArray) {
                    deal[dataArray[i]['name']] = dataArray[i]['value'];

                    var inputElements = document.getElementsByTagName('input');
                                    if (inputElements[i].type == 'text') {
                                                inputElements[i].value = '';
                                    }
                }
                appendDeal(deal);
            }
        });
        return false;
    });

        //Delete one deal
        $(document).on('click', '.delete-deal', function()
        {
              var link = $(this);
                    var dealId = link.data('id');
                    $.ajax({
                        method: "DELETE",
                        url: '/deals/' + dealId,
                        success: function(response)
                        {
                            alert("Дело № " + dealId +" удалено");
                            link.parent().remove();
                        },
                        error: function(response)
                        {
                            if(response.status == 404) {
                                alert('Такая задача не найдена!');
                            }
                        }
                    });
                    return false;
        });

        //Delete all deals
        $('#clear-deal-list').click(function()
        {
            var data = $('#deal-list').serialize();
            $.ajax({
                    method: "DELETE",
                    url: '/deals/all',
                    caches: false,
                    success: function(response)
                    {
                       $('#deal-list').empty();
                       alert('Все запланированные дела удалены');
                    },
                    error: function (response) {
                        if(response.status !== 200) {
                           alert('Внутренняя ошибка!');
                        }
                    }
            });
            return false;
        });

    $('#hide-deal-list').click(function(){
      $('.list').css('display', 'none');
    });

    $('#show-deal-list').click(function(){
      $('.list').css('display', 'block');
    });

    $(document).on('click', '.deal-change', function(){
      $('#deal-change-form').css('display', 'flex');
    });

        //Closing adding deal form
        $(document).on('click', '.deal-change', function(event){
            if(event.target === this) {
                $(this).css('display', 'none');
            }
        });
});