$(function () {
    //刷新按钮点击事件
    $('#refresh-btn').click(function () {
        getList(function () {
            alert('已刷新')
        })
    })
    getList()
})

function getList(callback) {
    $ajax({
        type: 'post',
        url: '/item_info/getItem',
    }, false, '', function (res) {
        if (res.code == 200) {
            setTable(res.data);
            if (callback != undefined) {
                callback();
            }
        }
    })
}

function setTable(data) {

    if ($('#table').html != '') {
        $('#table').bootstrapTable('load', data);
    }

    $('#table').bootstrapTable({
        data: data,
        sortStable: true,
        classes: 'table table-hover',
        idField: 'id',
        pagination: true,
        clickToSelect: true,
        locale: 'zh-CN',
        toolbar: '#table-toolbar',
        toolbarAlign: 'left',
        columns: [
            {
                field: 'id',
                // field: '',
                title: '序号',
                align: 'center',
                width: 50,
                formatter: function (value, row, index) {
                    return index + 1;
                }
            }, {
                field: 'ItemCode',
                title: 'ItemCode',
                align: 'left',
                sortable: true,
                width: 150
            }, {
                field: 'SKUID',
                title: 'SKUID',
                align: 'left',
                sortable: true,
                width: 150
            }, {
                field: 'ProductName',
                title: '商品名称',
                align: 'left',
                sortable: true,
                width: 150
            },{
                field: 'AssetID',
                title: 'AssetID',
                align: 'left',
                sortable: true,
                width: 150
            }, {
                field: 'Brand',
                title: '品牌',
                align: 'left',
                sortable: true,
                width: 150,
                formatter: function (value, row, index) {
                    return formatDate(new Date(value), "yyyy-MM-dd HH:mm:ss");
                }
            }, {
                field: 'SKUDesc',
                title: '商品简介',
                align: 'left',
                sortable: true,
                width: 150,
            }, {
                field: 'SKUShortName',
                title: 'SKUShortName',
                align: 'left',
                width: 150,
            }
        ],
        onClickRow: function (row, el) {
            let isSelect = $(el).hasClass('selected')
            if (isSelect) {
                $(el).removeClass('selected')
            } else {
                $(el).addClass('selected')
            }
        }
    })
}
