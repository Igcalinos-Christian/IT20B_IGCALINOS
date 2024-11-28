class chartFill{
    #jsonFile;
    #jsonContainer;
    constructor(jsonFile){
        this.#jsonFile = jsonFile;
    }

    getJson(){
        fetch(this.#jsonFile)
        .then(res => res.json())
        .then(values => values.map(element => {
            const color = element.colors;
            const numbers = element.values;

            console.log(color, numbers);

            this.postChart(color, numbers);
            }
        ))
        .catch(error => {
            console.error('Error fetching the JSON file:', error);
        });
    }

    postChart(colors, values){
        const ctx = document.getElementById('chartCanvas');

        console.log(colors, values);

        new Chart(ctx, {
            type: 'bar',
            data: {
            labels: colors, //labels
            datasets: [{
                label: '# of Votes',
                data: values, //values
                borderWidth: 1,
                backgroundColor: colors//background color
            }]
            },
            options: {
            scales: {
                y: {
                beginAtZero: true
                }
            }
            }
        });
    }
}

const json = new chartFill('charts.json');
json.getJson();