$(function () {
    //刷新按钮点击事件
    $('#refresh-btn').click(function () {
        getList(function () {
            alert('已刷新')
        })
    })
    getList();
})

$(function () {
    //导出按钮点击事件
    $('#send-btn').click(function () {
        alert("运行时间可能较长，请耐心等待！")
        $ajax({
            type: 'post',
            url: '/item_info/toExcel',
        }, false, '', function (res) {
            if (res.code == 200) {
                alert("发送成功！");
            }else{
                alert("发送失败！");
            }
        })
    })
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
                title: '序号',
                sortable: true,
                align: 'center',
                width: 100,
            }, {
                field: 'ItemCode',
                title: 'ItemCode',
                align: 'left',
                width: 200
            }, {
                field: 'SkuId',
                title: 'SkuId',
                align: 'left',
                width: 200
            }, {
                field: 'ProductName',
                title: '商品名称',
                align: 'left',
                width: 200
            },{
                field: 'AssetId',
                title: 'AssetId',
                align: 'left',
                width: 200
            }, {
                field: 'Brand',
                title: '品牌',
                align: 'left',
                width: 200,
            }, {
                field: 'SKUDesc',
                title: '商品简介',
                align: 'left',
                width: 200,
            }, {
                field: 'SKUshortName',
                title: 'SKUshortName',
                align: 'left',
                width: 200,
            }, {
                field: 'SKUSeries',
                title: '系列',
                align: 'left',
                width: 200,
            }, {
                field: 'SKUModel',
                title: '型号',
                align: 'left',
                width: 200,
            }, {
                field: 'SKUSpec',
                title: '参数',
                align: 'left',
                width: 200,
            }, {
                field: 'UserSKUCode',
                title: '产品目录',
                align: 'left',
                width: 200,
            }, {
                field: 'URL',
                title: '链接',
                align: 'left',
                width: 200,
            }, {
                field: 'Channel',
                title: '电商平台',
                align: 'left',
                width: 200,
            }, {
                field: 'ShopName',
                title: '店铺名称',
                align: 'left',
                width: 200,
            }, {
                field: 'IsAuthor',
                title: '是否授权',
                align: 'left',
                width: 200,
            }, {
                field: 'Daigou',
                title: '代购',
                align: 'left',
                width: 200,
            }, {
                field: 'OriginalPrice',
                title: '吊牌价',
                align: 'left',
                width: 200,
            }, {
                field: 'ActivityPrice',
                title: '页面活动价',
                align: 'left',
                width: 200,
            }, {
                field: 'GuidancePrice',
                title: '官方指导促销价',
                align: 'left',
                width: 200,
            }, {
                field: 'FinalPrice',
                title: '到手价',
                align: 'left',
                width: 200,
            }, {
                field: 'MarginPrice',
                title: '价差',
                align: 'left',
                width: 200,
            }, {
                field: 'MarginPercent',
                title: '折扣率',
                align: 'left',
                width: 200,
            }, {
                field: 'MarkdownPercent',
                title: '降价率',
                align: 'left',
                width: 200,
            }, {
                field: 'InsertDate',
                title: '最近低价时间',
                align: 'left',
                width: 200,
                formatter: function (value, row, index) {
                    return formatDate(new Date(value), "yyyy-MM-dd HH:mm:ss");
                }
            }, {
                field: 'ShopId',
                title: '店铺ID',
                align: 'left',
                width: 200,
            }, {
                field: 'ViewTime',
                title: '观察时间',
                align: 'left',
                width: 200,
                formatter: function (value, row, index) {
                    return formatDate(new Date(value), "yyyy-MM-dd HH:mm:ss");
                }
            }, {
                field: 'SellerLocation',
                title: '发货地',
                align: 'left',
                width: 200,
            }, {
                field: 'IsbreakPrice',
                title: '是否破价',
                align: 'left',
                width: 200,
            }, {
                field: 'Tariff',
                title: '进口税',
                align: 'left',
                width: 200,
            }, {
                field: 'TariffPrice',
                title: '含进口税到手价',
                align: 'left',
                width: 200,
            }, {
                field: 'AllPromotion',
                title: '所有促销信息',
                align: 'left',
                width: 200,
            }, {
                field: 'UsePromotion',
                title: '应用促销信息',
                align: 'left',
                width: 200,
            }, {
                field: 'SuperPromotion',
                title: '超级促销',
                align: 'left',
                width: 200,
            }, {
                field: 'ScrollSales',
                title: '近30天滚动销量',
                align: 'left',
                width: 200,
            }, {
                field: 'CommentNum',
                title: '评论数',
                align: 'left',
                width: 200,
            }, {
                field: 'StockNum',
                title: '库存',
                align: 'left',
                width: 200,
            }, {
                field: 'StockStatus',
                title: '在库状态',
                align: 'left',
                width: 200,
            }, {
                field: 'CreateTime',
                title: '数据生成时间',
                align: 'left',
                width: 200,
                formatter: function (value, row, index) {
                    return formatDate(new Date(value), "yyyy-MM-dd HH:mm:ss");
                }
            }, {
                field: 'TaskId',
                title: 'TaskId',
                align: 'left',
                width: 200,
            }, {
                field: 'ShopLab',
                title: 'ShopLab',
                align: 'left',
                width: 200,
            },
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
