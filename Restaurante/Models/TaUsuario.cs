    using System;
    using System.Collections.Generic;

namespace Restaurante.Models
{

        public partial class TaUsuario
        {
            public int IdUsuario { get; set; }

            public string Categoria { get; set; }

            public string Usuario { get; set; } = null!;

            public string Contrasena { get; set; }



        }
}   
