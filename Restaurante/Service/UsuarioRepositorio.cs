﻿using Restaurante.Models;

namespace Restaurante.Service
{
    public class UsuarioRepositorio : IUsuario
    {
        private Conexion conex = new Conexion();
        public void add(TaUsuario TaUsuario)
        {
            conex.TaUsuarios.Add(TaUsuario);
            conex.SaveChanges();
        }

        public void eleminar(int id)
        {
            conex.TaUsuarios.Remove(ObtenerPorid(id));
            conex.SaveChanges();
        }

        public IEnumerable<TaUsuario> GetUsuario()
        {
            return conex.TaUsuarios;
        }

        public void modificar(TaUsuario TaUsuarios)
        {
            var obj = conex.TaUsuarios.Where(tpro => tpro.IdUsuario == TaUsuarios.IdUsuario)
            .FirstOrDefault();

            obj.Categoria = TaUsuarios.Categoria;
            obj.Usuario = TaUsuarios.Usuario;
            obj.Contrasena = TaUsuarios.Contrasena;            
            conex.SaveChanges();
        }

        public TaUsuario ObtenerPorid(int id)
        {
            return conex.TaUsuarios.Where(tpro => tpro.IdUsuario == id).FirstOrDefault();
        }
    }
}
