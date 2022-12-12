let dateToHourMin = (date) =>
  date.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" });

let isUndefined = (variable) => variable === undefined;

export default {
  dateToHourMin,
  isUndefined,
};
