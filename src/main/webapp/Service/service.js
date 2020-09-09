function renderService(){
  TracomAcademy.Grid.apply({
        contentRender: "content",
        gridTitle: "Service",
        componentId: "service",
        url: "service",
        columns: [{
            header: "Id",
            dataIndex: "id",
            width: 20
        }, {
            header: "Type_id",
            dataIndex: "Type_id",
            width: 30
        }, {
            header: "Time",
            dataIndex: "Time",
            width: 30
            },
             {
                header: "Amount",
                dataIndex: "Amount",
                width: 30
                    },
        {
            header: "",
            dataIndex: "delete",
            width: 15,
            },
        {
            header: "",
            dataIndex: "edit",
            width: 15,
        },
        ],
        store: [],
        form: {
            id: "service-form",
            url: "service",
            items: [{
                label: "ID No.",
                name: "sid",
                id: "service.sid",
                type: "text"
            },{
                label: "Type_id",
                name: "stype_id",
                id: "service.stype_id",
                type: "text"
            },{
                label: "Time",
                name: "stime",
                id: "service.stime",
                type: "text"
              },{
                label: "Amount",
                name: "samount",
                id: "service.samount",
                type: "text"
             }]

        }
    });
}
