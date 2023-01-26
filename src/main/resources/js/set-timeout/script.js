tms = [];
document.getElementById('input1').addEventListener('keydown', () => {
    const tm = setTimeout(() => console.log("Stopped typing!"), 2000);
    tms.push(tm);
    console.log("tm=" + tm);
    tms.filter(x => x < tm).forEach(clearTimeout);
});

   
   
