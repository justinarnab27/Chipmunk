export const convertToHex = (byte: number): string => {
    let byteHex = byte.toString(16);
    return "0".repeat(Math.max(2 - byteHex.length,0)) + byteHex;
}
export const convertToBin = (byte: number): string => {
    let byteHex = byte.toString(2);
    return "0".repeat(Math.max(8 - byteHex.length,0)) + byteHex;
}
export const convertToDec = (byte: number): string => {
    let byteHex = byte.toString(10);
    return "0".repeat(Math.max(3 - byteHex.length,0)) + byteHex;
}

export const convertToBase = (byte: number, base: number) => {
    switch (base) {
        case 2:
            return convertToBin(byte);
        case 10:
            return convertToDec(byte);
        default:
            return convertToHex(byte);
    }
}