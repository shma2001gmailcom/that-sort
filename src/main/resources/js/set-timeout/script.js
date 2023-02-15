// tms = [];
// document.getElementById('input1').addEventListener('keydown', () => {
//     const tm = setTimeout(() => console.log("Stopped typing!"), 2000);
//     tms.push(tm);
//     console.log("tm=" + tm);
//     tms.filter(x => x < tm).forEach(clearTimeout);
// });
let timerId;
document.getElementById('input1').addEventListener('keydown', () => {
    // Clear any previously set timers
    clearTimeout(timerId);
    // Set a new timer to log the message after 2 seconds
    timerId = setTimeout(() => {
        console.log('Stopped typing!');
    }, 2000);
});

   
   
