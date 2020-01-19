class StringUtils {
  isEmpty (str) {
    return !str || str.length === 0;
  }
}

export default new StringUtils();
