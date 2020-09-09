function renderAccounts(){
    TracomAcademy.Grid.apply({
        contentRender: "content",
        gridTitle: "Accounts",
        componentId: "accounts",
        url: "accounts",
        columns: [{
            header: "Id",
            dataIndex: "id",
            width: 20
        }, {
            header: "Month",
            dataIndex: "Month",
            width: 30
        }, {
            header: "Year",
            dataIndex: "Year",
            width: 30
            },
             {
                header: "Expenditure",
                dataIndex: "Expenditure",
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
            id: "accounts-form",
            url: "accounts",
            items: [{
                label: "ID No.",
                name: "aid",
                id: "accounts.aid",
                type: "text"
            },{
                label: "Month",
                name: "amonth",
                id: "accounts.amonth",
                type: "text"
            },{
                label: "Year",
                name: "ayear",
                id: "accounts.ayear",
                type: "text"
              },{
                label: "Expenditure",
                name: "aexpenditure",
                id: "accounts.aexpenditure",
                type: "text"
             }]

        }
    });
}
