$(function(){
    const appendDeal = function(data){
        var dealCode = '<a href="#" class="deal-link" data-id="' +
            data.id + '">' + data.name + '</a>  ';
        $('#deal-list')
            .append('<div>' + dealCode
            + '<img id="delete-deal" src="https://ru.wargaming.net/clans/media/clans/emblems/cl_555/205555/emblem_64x64.png" width="34px">'
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
                var code = '<br><br><span>Дата: ' + response.date + '</span><br><br>';
                link.parent().append(code);
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
                }
                appendDeal(deal);
            }
        });
        return false;
    });

        //Delete deal
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
                    }
                    appendDeal(deal);
                }
            });
            return false;
        });
});