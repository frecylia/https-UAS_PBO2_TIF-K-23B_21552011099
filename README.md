UTS Pemrograman Berorientasi Obyek 2
Mata Kuliah: Pemrograman Berorientasi Obyek 2
Dosen Pengampu: Muhammad Ikhwan Fathulloh

Profil
Nama: Frecylia Eflyn
NIM: 21552011099

Studi Kasus: kasir Asuransi

Judul Studi Kasus
Sistem Informasi Asuransi Berbasis Java dengan SQLite untuk Manajemen Nasabah dan Klaim

Penjelasan Studi Kasus
Sistem ini bertujuan untuk membantu perusahaan asuransi dalam mengelola data nasabah, jenis polis asuransi (kesehatan dan jiwa), serta pengajuan klaim. Fitur utama meliputi registrasi dan login user untuk keamanan, penambahan data nasabah, perhitungan premi berdasarkan jenis asuransi dan umur nasabah, serta pencatatan klaim asuransi.

Penjelasan 4 Pilar OOP dalam Studi Kasus
Inheritance
Kelas AsuransiKesehatan dan AsuransiJiwa merupakan turunan dari kelas abstrak Asuransi, yang mewariskan atribut nama dan umur serta metode abstrak hitungPremi().

Encapsulation
Data penting seperti nama, umur, dan password user disembunyikan dalam kelas masing-masing dengan access modifier private atau protected, dan diakses melalui method getter/setter atau method khusus untuk keamanan.

Polymorphism
Implementasi metode hitungPremi() yang berbeda pada kelas AsuransiKesehatan dan AsuransiJiwa menunjukkan polymorphism, dimana objek kelas turunan bisa diperlakukan sebagai objek kelas induk dengan perilaku yang berbeda.

Abstraction
Kelas Asuransi merupakan kelas abstrak yang hanya mendefinisikan kerangka umum tanpa detail implementasi, memaksa kelas turunannya untuk mengimplementasikan metode hitungPremi() sesuai kebutuhan spesifik.

Demo Proyek
Github: https://github.com/frecylia/https-UAS_PBO2_TIF-K-23B_21552011099

Youtube: -
